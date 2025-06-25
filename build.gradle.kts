plugins {
    id("java")
    id("idea")
    id("signing")
    id("maven-publish")
    id("com.tddworks.central-portal-publisher") version "0.0.5"
}

val ossrhUsername: String by project
val ossrhPassword: String by project

val version: String by project
val group: String by project
val artifact: String by project

project.group = group
project.version = version

repositories {
    mavenCentral()
}

dependencies {
    compileOnly("org.jetbrains:annotations:26.0.2")
    compileOnly("com.google.code.findbugs:jsr305:3.0.2")
}
java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
    }
    withSourcesJar()
    withJavadocJar()
}
sourceSets {
    main {
        java {
            srcDir("src")
        }
        resources {
            srcDir("resources")
        }
    }
    test {
        java {
            srcDir("test")
        }
    }
}
idea {
    module {
        isDownloadJavadoc = true
        isDownloadSources = true
    }
}

tasks {
    compileJava {
        options.compilerArgs.add("-parameters")
        options.encoding = "UTF-8"
    }
    compileTestJava { options.encoding = "UTF-8" }
    javadoc { options.encoding = "UTF-8" }
}
publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            pom {
                name.set(project.name)
                groupId = group
                artifactId = artifact
                version = version
                description.set("A simple Marker Library for PaperMC")
                url.set("https://github.com/Hugo5000/Java-Annotations")
                licenses {
                    license {
                        name.set("GNU General Public License version 3")
                        url.set("https://opensource.org/license/gpl-3-0/")
                    }
                }
                developers {
                    developer {
                        name.set("Hugo")
                        email.set("noreply@hugob.at")
                    }
                }
                scm {
                    connection.set("scm:git:git://github.com/Hugo5000/Java-Annotations.git")
                    developerConnection.set("scm:git:ssh://github.com/Hugo5000/Java-Annotations.git")
                    url.set("http://github.com/Hugo5000/PaperMC-MarkerLib/tree/master")
                }
            }
            from(components["java"])
        }
    }
}

signing {
    sign(publishing.publications["mavenJava"])
}

sonatypePortalPublisher {
    authentication {
        username = ossrhUsername
        password = ossrhPassword
    }
    settings {
        autoPublish = false
    }
}