## Adapter-S

Standalone Scala language provider for Outlands 1.12.2 — it ships the Scala 3 runtime and declares the `scala`
language adapter to the loader.

The jar carries no mod class and no loading plugin. `mcmod.info` declares:

- a `scala` language provider (`top.outlands.ScalaLanguageAdapter`),
- the Scala versions that provider can serve,
- a declarative entry point so the provider still appears as an active mod.

### Artifacts

There are two shapes on purpose:

- **Maven artifact** (`top.outlands:adapter-s`) — the compiled output of `src` plus the declarative `mcmod.info`, with
  **no** `jarjar`. Its POM declares the Scala runtime (`org.scala-lang:scala-library`, `scala-parser-combinators_3`,
  `scala3-tasty-inspector_3`) as ordinary compile-scope Maven dependencies, so a development build resolves the official
  Scala jars from Maven Central transitively. This is the artifact a mod developer compiles against.
- **Release jar** (`build/libs/adapter-s-<version>.jar`) — the remapped player-facing jar. It still bundles the Scala
  runtime as `containedDeps` (`kind: LIB`) under `META-INF/libraries/`, because a player install cannot resolve Maven
  dependencies.

### Downloads:

- [Scala Legacy](https://github.com/Project-Outlands/Adapter-S/releases/tag/2.11.1), use that if you are updating
  from Forge and/or don't know what is going on
- [Scala](https://github.com/Project-Outlands/Adapter-S/releases/tag/3.4.1), will crash with most existing mods, use it
  only if you know what you are doing
- You cannot install them together!
- There is only one Scala 3 mod for 1.12.2 (OpenComputers Rescaled)

### Development (temporary):

1. Add
```groovy
maven {
    name "outlandsReleases"
    url "https://maven.outlands.top/releases"
}
```
to your repositories

2. Add `modDevImplementation "top.outlands:adapter-s:3.10.0"` to your dependencies. The Scala runtime comes with it
   from Maven Central; you do not need to add those coordinates yourself.

3. Set your Scala version with the Scala plugin's
   [documentation](https://docs.gradle.org/current/userguide/scala_plugin.html). Example:
```groovy
scala {
    scalaVersion = '3.10.0'
}
```

4. Set `modLanguage = "scala"` in `@Mod()`. Optionally pin the runtime with
   `modLanguageVersion = "3.10.0"` (or the same key in your `mcmod.info` entry); Adapter-S declares the versions it
   provides in its `jar.providerVersions` section, so a mismatch fails during discovery instead of inside `preInit`.
