# Workflow level

In all of my examples I will use the domain outlined below.
I intentionally left this domain specification vague, as the actual details doesn't matter much.

There exists a world called **Buerocracy**.

In **Buerocracy** you can be:
- a **application creator**
- a **head buerocrat**
- a **bottom buerocrat**
- a **application receiver**

As an **application creator**:
- you can fill application forms (e.g. application for student visa)
- you can send in application forms

As a **head buerocrat**:
- you can give the job of processing an application to a bottom buerocrat

As a **bottom buerocrat**:
- you can process the application (e.g. deny a driver's license, or ask for more documents)

As an **application receiver**:
- get notifications about applications

## 1. Use Lambdas instead of local variables

> Is it feasible to provide interaction via lambdas in the workflow level?

Local variables break the flow of the code, and most of the time they are the product of leaky abstractions.
Therefore I chose to use lambdas pretty heavily.

For example, imagine you keep references to two actors in local variables.
You might get the wrong feeling that you can use their methods in any order, like this

```
ApplicationCreator creator = /* */;
creator.startsApplication(); // when this executes we login and navigate to a specific page
MasterBuerocrat masterBuerocrat = /* */;
masterBuerocrat.assignsApplicationToBottomBuerocrat(); // when this executes we switch to another user and navigate to some other page
creator.whatever(); // at this point application creator should not be called, because we are logged in as another user
```

To clearly communicate the 'lifetime' of a 'user session' and reduce boilerplate we can use lambdas and functional interfaces:

```
Buerocracy buerocracy = /* implementation */;
buerocracy.asApplicationCreator(creator -> {
	creator.startsApplication();
	...
}).asMasterBuerocrat(masterBuerocrat -> {
	masterBuerocrat.assignsApplicationToBottomBuerocrat();
});
```

## 2. Gherkin-like high level *sentences*

> Is it feasible to decouple high level tests from any actual implementation?

I wanted to write high level tests which tell a story (or at least try).
The specs were unhelpful in this aspect, and I was told that gherkin is a no-go.

So I came up with the not-so-bright solution, to hide all the scene objects, and `tell what, not how`.

Take a look at this [example](src/test/java/io/github/rkeeves/worfklow/BuerocracyTest.java)

This comes with a hefty price: state is managed by the Buerocracy context-like object.

You can see that some magic must be going on in the `asApplicationCreator` method:
```
Buerocracy asApplicationCreator(Function<ApplicationCreator, String> function);
```

It accepts a `Function`.
So basically this method asks you: *Give me a function to call, I'll call that, get the result, and I'll store it*
Why is this needed?
In later calls, all other actors will use this application id.

This is quite a hacky solution, but otherwise you'd have to manually pass the id around like this:
```
    }).asHeadBuerocrat(headBuerocrat -> {
            headBuerocrat.assignsApplicationToBottombuerocrat(applicationId);
```

Yes I know, that this is not the cleanest way, but otherwise you'd be bogged down into the technicalitites of carrying data around.

For instance, you'd have to enter even the bottom buerocrat's identification number/name/etc.
So a simple sentence like this:
```
    }).asHeadBuerocrat(headBuerocrat -> {
            headBuerocrat.assignsApplicationToBottombuerocrat();
```
Would become:
```
    }).asHeadBuerocrat(headBuerocrat -> {
            headBuerocrat.assignsApplicationToBottombuerocrat(applicationId, configration.getBottomBureocrat());
```

I thought that keeping the tests readable is more important, than keeping the context object stateless.

But I'd like to nkow how you'd approach this.
(The application id can only be decided runtime.
Aka when the user creates the application he can read it from the form.
This system assigned identifier is the only unique identifier.)

## 3. Configuration injection

> How would you handle injection of configuration?

Dependending on the environment user credentials, already existing entities etc. can vary.
To overcome this you must feed this data into the test from a config file (like spring's config processor or something).
If you leave this to the API user, his code will look like this in the best case (a bit like java faker way)

```
// configuration is possibly autowired
final ApplicationCreator creator = configuration.users().any();
final MasterBuerocrat masterBuerocrat = coniguration.masterBuerocrats().someSpecific();
final BottomBuerocrat bottomBuerocrat = coniguration.bottomBuerocrats().someSpecific();
```

But this gets uglier and uglier as you add more variables into the system.
And remember: most of these 'variables' are constants. 
The only reason why they are variables is that the environment's data differs.

On the other hand, using pico or spring we can use configuration class intances etc. and inject them into anything.
If we need multiple instances of some T type which needs this configuration, we can move the instantiation of T
into a factory, which can be a single instance.
Now that we have a single point of entry (the factory instance), we can inject all depdendencies into that.
(Basically T becomes a prototype bean of sorts.)

My dumb solution is the following:

I basically have yml files and use spring's config processor to map them onto classes (intances).
These property instances are spring managed, so in the tests I simply inject the root property instance,
(which has all the finer grained properties or property classes as fields)

```
// Configuration support :)
class WhackyProperties {
    
    Users users;
    
    // ... more classes wrapping properties
}

@Autowired
WhackyProperties whackyProperties;

@Test
void test {
    final Buerocracy buerocracy = new DefaultBuerocracy(whackyProperties);
}
```

But you can promote this easily to a factory (which is managed by spring/pico):
```
@Autowired
BuerocracyFactory factory;

@Test
void test {
    final Buerocracy buerocracy = factory.create();
}
```

## 4. Enforcing call order

> Do you think that enforcing the call order compile time is a bit overkill?

As you probably saw already, the API does not shield you from doing *dumb* things.

For example take a look at this:

```
 buerocracy.asApplicationCreator(applicationCreator -> {
            // The line below always produces error
            applicationCreator.fillsApplication(application -> {
                application.kind().set(ApplicationKind.STUDENT_VISA.toString());
                application.buerocraticEthics().set(Region.HUN.toString());
                application.fastTrackWithBribery().isVisible().set("Hell, no");
            });
            // Because this must come beforehand
            applicationCreator.startsApplication();
```

The above will not give you any compile time warnings that you did something wrong, but it'll fail runtime miserably.

I know that this could be solved by enforcing call order compile time via the type system, like in this
[neat example](https://github.com/iluwatar/java-design-patterns/tree/master/step-builder)

My rationale behind not enforcing is the following:
- If the business logic changes it is hard to rewire the type system
- It adds more complexity to an already too complex project

*(I had an iteration of the codebase which enforced call order, so I'm not asking about how it can be done.
I simply want to know whether - in your experience - the added safety net to the API caller 
is worth the additional complexity at the implementation side.)*

Before you ask:
Yes, this simple scenario is avoidable with some method reshuffling.
But in the real project there exist some operation B which can be done if and only if an operation A was already done.

In a nutshell it is something like this (but with 7 steps each doing some insanely complicated actionchain on the ui :)
```
makePaste();
// you can decide whether to stop now or continue to bakePaste
bakePaste();
// you can decide whether to stop now or continue to putCherryOnTop
putCherryOnTop();
```