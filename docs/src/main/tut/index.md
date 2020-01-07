---
layout: home
technologies:
 - first: ["Scala", "The yai toolkit is mostly written in Scala"]
 - second: ["SBT", "The yai toolkit uses SBT extensively"]
---

# yai toolkit

The **yai toolkit** ~~is~~ will be a set of plugins, libraries and easily configurable micro services which will automate the creation and supervision of data ingest jobs into a data lake.

## Components

In my overactive imagination the yai toolkit could be made up of many different components. Here are a couple ideas

- A standardised API to define ingest jobs using yaml/json files
- Yaml/json based source code generation
- A graphical interface to get a quick overview over the defined ingest jobs and their status
- Provide most common implementation of these
- Service deployment helpers

However currently I only work on the **api specification** and **code generation** plugin