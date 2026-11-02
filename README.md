# NeoForge 1.21.1 Mod Template

## Open and run

1. Open this folder as a Gradle project in IntelliJ IDEA and allow Gradle sync to finish.
2. In **Settings → Build, Execution, Deployment → Build Tools → Gradle**, set **Gradle JVM** to **Java 21**. The project also declares a Java 21 toolchain.
3. Pick **Minecraft Client** from the run configuration selector and click Run. The first launch downloads Minecraft and NeoForge artifacts and can take several minutes.
4. Pick **Build Mod** to make a distributable JAR under `build/libs/`.

The same tasks can be run from a terminal: `gradlew.bat runClient`, `gradlew.bat runServer`, and `gradlew.bat build`.

## What is included

A Java 21 starter based on the official NeoForge MDK using ModDevGradle. It registers a sample block and item, a creative tab, a config, and common/client setup hooks. Gradle run tasks are configured for client, server, GameTest server, and data generation.

## Customize

Change the mod ID, display name, version, and license in `gradle.properties`. The example ID is `startermod`; if you change it, also update `StarterMod.MODID`, the Java package, and the `assets/startermod` resource folder. `neoforge.mods.toml` is generated from the Gradle properties.

Current target: Minecraft 1.21.1, NeoForge 21.1.264, Java 21.

## References

- [NeoForge 1.21.1 Getting Started](https://docs.neoforged.net/docs/1.21.1/gettingstarted/)
- [Official ModDevGradle MDK](https://github.com/NeoForgeMDKs/MDK-1.21.1-ModDevGradle)

The template files are covered by `TEMPLATE_LICENSE.txt`.
