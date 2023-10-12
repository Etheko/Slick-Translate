pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()

    }
}


dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven {
            name = "clojars.org"
            url = uri("https://repo.clojars.org")
        }
    }
}

rootProject.name = "My Application"
include(":app")
