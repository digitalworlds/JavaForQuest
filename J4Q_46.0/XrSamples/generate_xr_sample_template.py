#!/usr/bin/python
# Copyright 2004-present Facebook. All Rights Reserved.

# This script is used for generate XrSample from the beginning by copying over the XrAppBase and
# modify all necessary field based on the extension name

import errno
import os
import shutil


def CopyDist(src, dst):
    try:
        shutil.copytree(src, dst)
    except OSError as exc:  # python >2.5
        if exc.errno in (errno.ENOTDIR, errno.EINVAL):
            shutil.copy(src, dst)
        else:
            raise


def BuildXrSample(extName):
    nameToken = extName.split("_")
    appName = "".join([x.capitalize() for x in nameToken])
    folderName = "Xr" + appName

    targetRootPath = "//arvr/projects/xrruntime/mobile/XrSamples/" + folderName
    appClass = folderName + "App"
    appTitle = folderName + " Sample"
    packageName = "com.oculus.xrsamples." + folderName.lower()
    targetName = "xrsamples_" + folderName.lower()

    dirname = os.path.dirname(__file__)
    src = os.path.join(dirname, "XrAppBase")
    dst = os.path.join(dirname, "{}".format(folderName))
    CopyDist(src, dst)

    excludeDir = ["assets"]

    for dp, dn, filenames in os.walk(dst):
        dn[:] = [d for d in dn if d not in excludeDir]
        for f in filenames:
            if f == ".DS_Store":
                continue
            fileName = os.path.join(dp, f)
            print(fileName)

            # Read in the file
            with open(fileName, "r") as file:
                fileData = file.read()

            # Replace
            fileData = fileData.replace(
                "//arvr/projects/xrruntime/mobile/XrSamples/XrAppBase", targetRootPath
            )
            fileData = fileData.replace(
                "XrSamples:XrAppBase", "XrSamples:" + folderName
            )
            fileData = fileData.replace("XrAppBaseApp", appClass)
            fileData = fileData.replace("XrAppBase", folderName)

            fileData = fileData.replace("com.oculus.sdk.xrappbase", packageName)
            fileData = fileData.replace("xrsamples_xrappbase", targetName)
            fileData = fileData.replace("xrappbase", folderName.lower())

            fileData = fileData.replace("Xr App Base", appTitle)

            # Write the file out again
            with open(fileName, "wb") as file:
                file.write(bytes(fileData, "UTF-8"))


def main():
    extName = input(
        """What is your app's name?
        Expectation:
        Input: <name>_<of>_<your>_<app>
        App Folder: Xr[NameOfYourAPP]
        Target: xrsamples_xr[nameofyourapp]
        Package: com.oculus.xrsamples.xr[nameofyourapp]
        App Class Name: Xr[NameOfYourApp]App\n"""
    )
    BuildXrSample(extName)


if __name__ == "__main__":
    main()
