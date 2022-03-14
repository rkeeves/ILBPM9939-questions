# Questions on API design

> DO NOT TRY TO RUN ME!
> 
> These codes are basically snippets only,
> and they don't do anything at all, not even a simple sout :)

How to design a convenient API for ui tests?

I've broken down the questions based on granularity into separate md files.
- [Widget level questions](WIDGET.md)
- [Scene level questions](SCENE.md)
- [Workflow level questions](WORKFLOW.md)

## Overview

The task is to design an API for one nasty application.

The application contains 100+ different dialogs and pages.

No REST, no DB data preload/test data setup is possible.

If - for instance - you must test a non-idempotent functionality,
you must create all the necessary preconditions via UI.

_(E.g. Want to test the deletion of 10 complex entities?
Well, you'll have to click through the ui to create 10.)_

Tests for workflows CANNOT be broken down into smaller separate subtests.
The user/ba wants the tests to click through a full multi user workflow, with all its flaky messy glory.

The ui tests ask for multiple levels of granularity.
Broadly speaking, these can be categorized into three levels:

- widget (E.g.: Is the field visible? I select the 'Bar' option of the select.)
- page (E.g.: Is the 'Create Cake' dialog open? I create a cake and save it.)
- workflow (E.g.: If I order a cake do I get it)