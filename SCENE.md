# Scene level

A scene is basically a page object, I just wanted to use a different word.

## 1. Navigation methods returning other scenes

> Should the navigation methods return other scenes?

All the guides on this matter say yes without getting into much detail.

I don't think that this question is so simple.

Some problems arise if you return refs to other scenes:
- Who instantiates them?
- Who stores them?
- How did the scene - who return the ref - acquire the ref in the first place?
- Does this directed graph contain any loops etc.?

The guides on the internet don't really dive deep into this graph problem but this can get you killed pretty fast.

Imagine you don't care about math and graphs and just want to write some tests so your boss can be happy:
1. You create the scenes
2. You're a smart boy, so you make them stateless
3. You're a smart boy, so you use pico or spring to store them
4. You use setter injection to overcome the dependency cycle
5. Everything works correctly
6. Now your tests require a fully built application context
7. Now you realize that you are entirely bound to pico/spring etc.
8. User decides that SceneB can now be reached from SceneC
9. Now you probably have to change a ctor injection to setter injection

Take a look at:
- [Not returning example](src/test/java/io/github/rkeeves/scene/nav/notreturning/SceneNavigationNotReturningInstancesTest.java)
- [Returning example](src/test/java/io/github/rkeeves/scene/nav/returning/SceneNavigationReturningInstancesTest.java)

I'm still undecided whether *returning instances* is a good option at all in the long run.
*(I'm not against it entirely, but I must admit that it comes with a lot of additional headaches.)*

## 2. Feature layer around the widgets

> Should the scene be layered?

### 2.1. Just the widgets

Example: [Plain](src/test/java/io/github/rkeeves/scene/feature/plain/PlainSceneTest.java)

The first approach is to simply store the *dom* and use it in the tests.
This way you are outsourcing all logic to the tests.

If some day you need to reuse something you'll have to come up with ugly utility classes and methods.

### 2.2. Widgets plus a ton of methods

Example: [Methods](src/test/java/io/github/rkeeves/scene/feature/methods/MethodsSceneTest.java)

With this approach you move the methods which would be in a utility class into the class itself.

This is what most people advise.

Well... as you can see, as the number of *execution* paths go up, you can create a class with 1000 lines.

Also, you can never be able to fully block access to your fields, beacuse a weird test might need direct access.

If you don't provide direct access then you'll have to provide new methods, and you'll start growing that 1000 line count
into the comfortable 1700 range. [These things exist](https://youtu.be/xKQPrQulmbY?t=90).

The worst of it: now you have 500 visible methods.
If a new guy joins and wants to use he'll have to look through all methods.

## 2.3. Feature wrapper

Example: [Methods](src/test/java/io/github/rkeeves/scene/feature/feature/FeaturesSceneTest.java)

This approach doesn't solve the issues of method and class explosion, 
but groups the methods together into different classes.
This gives the **impression** that the code is more organized
(but it is still a not well-defined, unprovable mess :) ).

This is simply a decoration on an otherwise burning nuclear waste dump, 
but for me at least these decorations can make the code more readable.

## 3. Interactions via lambdas

> Is it feasible to provide interaction via lambdas?

Even tough the situation is dire, there are some sets of widgets which can be grouped together, 
because they are somehow connected to each other semantically.

For example: Imagine a table which has a lot of child elements, but they act together as one component.
*(plus paginator, plus column sorters etc.)*

If you always declare a local variable the code can get quite lengthy.
Also, you tend to lose your track when debugging.

```
Scene scene;
Table table = scene.getTable();
table.hasRowCount(10);
Row row = table.findRowByName("a");
row.getAge().is(20);
...
```

Another approach is to use lambdas, to relieve you from declaring a local variable.

Take a look at [this example](src/test/java/io/github/rkeeves/scene/lambdas/LambdasTest.java)
*(Don't worry about that *Finder* class, it is a technical detail
(instead of wrapping an object around each row and iterating through rows,
direct css/xpath-ing is much faster, so that class is responsible for that thing only.
This gets handy with row counts of 100+))*