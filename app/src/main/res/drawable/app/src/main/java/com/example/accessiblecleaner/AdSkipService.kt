package com.example.accessiblecleaner

import android.accessibilityservice.AccessibilityService
import android.view.accessibility.AccessibilityNodeInfo
import android.view.accessibility.AccessibilityEvent
import android.util.Log

class AdSkipService : AccessibilityService() {

    companion object {
        private const val TAG = "AdSkipService"
    }

    override fun onAccessibilityEvent(event: AccessibilityEvent) {
        val rootNode = rootInActiveWindow ?: return
        findAndClickSkipButton(rootNode)
    }

    private fun findAndClickSkipButton(node: AccessibilityNodeInfo?) {
        if (node == null) return

        val text = node.text?.toString()?.lowercase() ?: ""
        val viewId = node.viewIdResourceName ?: ""

        if (text.contains("skip ad") || text.contains("skip") || viewId.contains("skip_ad_button")) {
            if (node.isClickable) {
                node.performAction(AccessibilityNodeInfo.ACTION_CLICK)
                Log.d(TAG, "Ad skipped successfully!")
                return
            }
        }

        for (i in 0 until node.childCount) {
            findAndClickSkipButton(node.getChild(i))
        }
    }

    override fun onInterrupt() {
        Log.d(TAG, "Accessibility Service Interrupted")
    }
}
