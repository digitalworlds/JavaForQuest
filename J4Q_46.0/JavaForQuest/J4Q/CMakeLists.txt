if(ANDROID)	
    find_package(OpenXR REQUIRED CONFIG)
    
    file(GLOB_RECURSE CPP_SOURCES "Src/*.cpp")
    file(GLOB_RECURSE C_SOURCES   "Src/*.c")

    add_library(j4q_native SHARED
        ${CPP_SOURCES}
        ${C_SOURCES}
    )

    target_include_directories(j4q_native PRIVATE
    Src
    ${CMAKE_SOURCE_DIR}/../OpenXR/Include
    )

    target_link_libraries(j4q_native
        PRIVATE
            android
            log
            OpenXR::openxr_loader
            native_activity_framework
    )

    set_target_properties(j4q_native PROPERTIES LINK_FLAGS "-u ANativeActivity_onCreate")
endif()