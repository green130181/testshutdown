LOCAL_PATH:= $(call my-dir)
include $(CLEAR_VARS)

TARGET_ARCH_ABI := armeabi
LOCAL_MODULE_TAGS := optional

LOCAL_STATIC_JAVA_LIBRARIES := android-support-v4 
LOCAL_STATIC_JAVA_LIBRARIES += android-support-v7-appcompat 

LOCAL_SRC_FILES := $(call all-java-files-under, src)

LOCAL_RESOURCE_DIR = \
	$(LOCAL_PATH)/res \
    frameworks/support/v7/appcompat/res
LOCAL_AAPT_FLAGS := \
        --auto-add-overlay \
        --extra-packages android.support.v7.appcompat

LOCAL_PACKAGE_NAME := testshutdown
LOCAL_OVERRIDES_PACKAGES := testshutdown


LOCAL_CERTIFICATE := platform

include $(BUILD_PACKAGE)

##################################################
include $(CLEAR_VARS)

include $(BUILD_MULTI_PREBUILT)

# Use the following include to make our test apk.
include $(call all-makefiles-under,$(LOCAL_PATH))
