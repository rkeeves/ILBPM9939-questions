# Widget level

A `widget` can be:
- an **input**
- a **select**
- a **button**
- a complex set of **WebElement**s generated by a ui library, which at the end of the day look like a **select**

## 1. Querying eagerly or with implicit waits MUST be distinct

> Is my assumption right that dom querying behavior must always have 2 distinct kinds?

*(Aka we cannot possibly unify and hide them from the user.
They differ so much, that this detail must be present in the API for the user to decide 
which one it wants to use depending on its needs.)*

I built upon [Selenide](https://selenide.org/) which is a neat little wrapper around classic Selenium.

I reused some of its ideas here and there.

One thing that I've found so far correct - due to the dynamic behavior of the ui (ajax updates) - 
is the distinction between **fetch**ing and **assert**ing.

By **assert** I mean: Assert that a **WebElement**'s property is some value/exists etc.

By **fetch** I mean: Get some property of the **WebElement**.

The above two differ greatly.

In the case of **assert**: I know what condition I want, and I can implicitly wait until the ui fulfills that condition, or timeout.

In the case of **fetch**: I cannot implicitly wait, because I don't know the condition.

For example, imagine visiblity tests.

```
SomeElement e;
e.mustBeVisible(); // I can implicitly wait until the element is visible
e.getVisible(); // I cannot implicitly wait, because I don't know the condition to wait for
```

So my assumption, which I cannot prove, is that you cannot unify these two kinds of query behaviors
and you must provide access to both for the API user.

## 2. Classic or Visitor or Command?

> Which pattern is more viable Classic or Visitor or Command?

The widgets can be modelled in a lot of ways.

Some possible approaches:
- [Classic OOP](src/test/java/io/github/rkeeves/widget/pattern/classic/WidgetClassicPatternTest.java)
- [Visitor](src/test/java/io/github/rkeeves/widget/pattern/visitor/WidgetVisitorPatternTest.java)
- [Command](src/test/java/io/github/rkeeves/widget/pattern/command/WidgetCommandPatternTest.java)

### 2.1. Classic

Example: [Classic OOP](src/test/java/io/github/rkeeves/widget/pattern/classic/WidgetClassicPatternTest.java)

The vanilla way of doing it is pretty straight forward.
Each widget gets its own class, implements methods etc.

If you want some **order in the madness**, you can force common behavior via interfaces.

### 2.2. Visitor

Example [Visitor](src/test/java/io/github/rkeeves/widget/pattern/visitor/WidgetVisitorPatternTest.java)

Basically double dispatch.

Visitors:
- do something specific (like getting the value)
- provide multiple overloads which accept the different types of widgets

Visitables (the input, select etc.):
- provide methods which accept a visitor, and in the method body, they call the specific overload of the visitor's methods
- provide access to the dom elements which the visitor can act upon

It has some perks, but it's much more complicated.
In my opinion this is overengineering for no reason.

### 2.3. Command

`Command` has the advantage of getting rid of state.
*(Even though in the example I still hold on to a `WebElement` reference in the widget classes for simplicity's sake.)*
This can come in handy if the user wants to do something entirely whacky thing,
like instantiating 2 different drivers and doing a test which involves both.
(Imagine a scenario, where two agents/users of different roles must interact with each other,
and none of them can be mocked away in tests.)

Also, with `Command`, it can be valuable to be able to decouple the widget's behavior from its actual implementation.
For example: different JSF based ui tag libraries provide special tags.
Like the default h:selectOneMenu renders a simple select, but p:selectOneMenu renders an engineering deficit.
If we interact with a `SelectOneMenu` interface in the tests, and we make the page object responsible for deciding on
the actual implementation, we can make the tests at least a bit more durable even if the devs keep changing the xhtml,
due to some reason.


## 3. Interface defaults for common behavior

> Is the usage of interface defaults acceptable in this scenario? 

Most queries can be resolved the same way.

Take for example visibility.

Even if you have a widget which must interact with a lot of *WebElement*s, 
you are still going to check the visibility of the container element.

Take a look at this [example](src/test/java/io/github/rkeeves/widget/ability/notchained/AbilityNotChainedTest.java)

## 4. Passing type information in a kind of unsafe, but still compile-time checkable way

> Is the below approach acceptable in this scenario?

Remember that for method chaining, we must be able to return a properly typed reference to the *this*.

As we all know Java is a big cheater when it comes to generics (type erasure).

This whole problem was handled in a neat way with Java's clone:
- You are out of luck, all type information is erased. Good luck, have fun!

Instead of this approach we can use some dirty tricks.

Take a look at this [example](src/test/java/io/github/rkeeves/widget/ability/chained/AbilityChainedTest.java)
