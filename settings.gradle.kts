pluginManagement {
    repositories {
        google()
        mavenCentral { url = uri("https://maven.pkg.jetbrains.space/public/p/compose/dev") }

        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
}

rootProject.name = "RickAndMortyMasterListApp"
include(":app")
