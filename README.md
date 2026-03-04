![Asset 1@3x](https://github.com/digitalworlds/JavaForQuest/blob/main/docs/img/banner1000x2000.png?raw=true)
# 🌟 J4Q (Java For Quest) Project
Digital Arts & Sciences Programs | Digital Worlds Institute | University of Florida

The J4Q project demonstrates how to create Virtual Reality apps for Meta Quest headsets as Native Android applications using Java in Android Studio. This project can be used as a setup for learning how to develop from scratch a basic game engine for Virtual Reality in Java. It can also be used along with a structured curriculum for teaching various topics related to game engine development such as: 3D Transformations using Matrices and Vectors, 3D geometry using triangular mesh, Animation loop, Shaders using GLSL, Controller input, Vibration feedback, and many other topics.  

![Asset 1@3x](https://github.com/digitalworlds/JavaForQuest/blob/main/docs/img/Illustration.png?raw=true)

## Citation
If you use this library please cite the paper, in which we introduced J4Q:
Barmpoutis, A., Guo, W. and Said, I., 2023. Developing Mini VR Game Engines as an Engaging Learning Method for Digital Arts & Sciences. 13th IEEE Integrated STEM Education Conference, pp. 33-36. [https://doi.org/10.1109/ISEC57711.2023.10402239](https://digitalworlds.github.io/angelos/page/developing-mini-vr-game-engines-as-an-engaging-learning-method-for-digital-arts-sciences/index.html)

🥈 This paper won the Best Paper Award (2nd place) in the 13th IEEE Integrated STEM Education Conference at Johns Hopkins University, on March 11, 2023.

## 📔 Contents

The project contains several sample VR apps located inside the `J4Q_XX.0/JavaForQuest/Projects` folder. For Meta Quest v1 headsets use J4Q_46.0, for Meta Quest v2 & v3 use J4Q_85.0.
- 🔭 EndlessUniverse: A full-scale demo of the J4Q API
- ⏳ Sandbox: An empty project for you to start experimenting
- ⏳SandboxPhone: If you don't have a headset this template can help you develop and test J4Q apps on a phone or Android emulator.
- 🔷 HelloPolygon: An introduction on how to create 3D objects
- 🎨 HelloTexture: An example on how to map textures onto 3D geometry
- ♾️ HelloNormals: A tutorial on how to encode surface normal vectors
- 🎞️ HelloAnimation: An example on how to create simple animations
- 🔧 HelloObjectMaker: A simple API for composing 3D objects using primitive shapes
- 💬 HelloObjectClass: An example on how to define 3D objects as classes using object-oriented programming
- 🖲️ OBJViewer: This app demonstrates how to open a 3D model from an OBJ-formatted file
- 🎮 HelloControllers: A tutorial on VR controllers and how to use their input (pose, buttons, squeezes, joysticks) and output (vibration) 

## ⚙️ How to setup and run J4Q:

The following instructions will guide you step by step on how to setup and run J4Q in your system. The instructions include details for the following steps:

- Installation of required software. 
- Configuration of headset.
- Opening in Android studio the project file: `J4Q_XX.0/JavaForQuest/Projects/EndlessUniverse/build.gradle`

### 1. Prerequisites

You need to install the following software to be able to compile Android apps for Meta Quest headsets:

- [x] Android Studio: https://developer.android.com/studio
- [x] Open the SDK Manager of Android Studio and install the SDK Platform 8.0 (Oreo) API Level 26
- [x] In the SDK Manager of Android Studio, go to the SDK Tools tab and check the box `Show Package Details` and install: 1) NDK (25.2.9519653), 2) CMake (3.22.1)

### 2. Configure your Meta Quest headset

- [x] Update the headset to the latest firmware and software by going to Quick Settings (where the time and battery status is shown) -> Settings -> Software Update
- [x] Enable Developer Mode by using the Meta Quest app

### 3. Run the J4Q (Java For Quest) Project

- [x] There are many Android studio projects inside the folder `J4Q_46.0/JavaForQuest/Projects`. Select a project and open in Android Studio the file `builde.gradle` located inside the project folder. For example for the EndlessUniverse project open the file: `J4Q_46.0/JavaForQuest/Projects/EndlessUniverse/build.gradle`
- [x] If there are errors during the build process, follow our troubleshooting guide at the end of this document.
- [x] Connect your Quest headset to your development computer using the charging cable.
- [x] In the headset accept the pop-up permission dialog.
- [x] When the name of your headset device appears in the device list in the toolbar of Android Studio, click on the Run button to compile the project and install it on your headset.
- [x] Test the J4Q Demo app in your headset.
- [x] In general, you can find and run the app in your Quest headset in your Library in the category 'Unknown Sources'. 

## 🧪 Tested with the following system configuration:

- Android Studio Narwhal Feature Drop | 2025.1.2 Patch 1
- Android SDK Platform 14.0 (UpsideDownCake) API Level 34
- Gradle 8.1.4
- NDK 27.0.12077973
- CMake 3.22.1
- Operating Systems used for development: Tahoe 26.1
- Meta Quest 1 (J4Q_46.0), Meta Quest 2 & 3 (J4Q_85.0)

## 🚩 Troubleshooting

### The application appear as a black window in the headset

The Meta Quest 1 headset can only run applications made with J4Q_46.0. If you try to run applications made with J4Q_85.0 they will appear as black windows due to incompatible Meta SDK version.

### Compatible side by side NDK version was not found. 

The error message could also appear in other variations such as: Gradle sync failed: NDK Not Configured

1. Verify that you have completed the prerequisite installation and particulraly the installation of NDK.

### Setting Android Studio as a Developer Tool in Mac

In MacOS it is helpful if you set Android Studio as a Developer Tool. 

1. To do that, first enable Developer Mode by opening the Terminal and typing `spctl developer-mode enable-terminal`. 
2. Then in System Preferences go to Security & Privacy and in the Privacy Tab, go to the option Developer Tools and add Android Studio.

## 🤝 Acknowledgements

1. This project was created using the Oculus OpenXR Mobile SDK 46.0 and 85.0 https://developer.oculus.com/downloads/package/oculus-openxr-mobile-sdk/
2. Special thanks to Angelos Barmpoutis, Ines Said, and Wenbin Guo for their contributions to this project.
3. We would like to thank the University of Florida for supporting the development of this project.

## ✍ Cite as

Barmpoutis, A., Guo, W. and Said, I., 2023. Developing Mini VR Game Engines as an Engaging Learning Method for Digital Arts & Sciences. 13th IEEE Integrated STEM Education Conference, pp. 33-36. 
[https://doi.org/10.1109/ISEC57711.2023.10402239](https://digitalworlds.github.io/angelos/page/developing-mini-vr-game-engines-as-an-engaging-learning-method-for-digital-arts-sciences/index.html)

