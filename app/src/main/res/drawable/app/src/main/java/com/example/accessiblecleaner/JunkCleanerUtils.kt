package com.example.accessiblecleaner

import android.content.Context
import android.util.Log
import java.io.File

object JunkCleanerUtils {
    private const val TAG = "JunkCleanerUtils"

    fun cleanJunkFiles(context: Context): Boolean {
        return try {
            var cleanedAny = false
            val cacheDir = context.cacheDir
            if (cacheDir != null && cacheDir.exists()) {
                cleanedAny = deleteRecursive(cacheDir)
            }
            
            val externalCacheDir = context.externalCacheDir
            if (externalCacheDir != null && externalCacheDir.exists()) {
                val extCleaned = deleteRecursive(externalCacheDir)
                if (extCleaned) cleanedAny = true
            }
            
            Log.d(TAG, "Junk cleaning completed. Cleaned: $cleanedAny")
            cleanedAny
        } catch (e: Exception) {
            Log.e(TAG, "Error cleaning junk files", e)
            false
        }
    }

    private fun deleteRecursive(fileOrDirectory: File): Boolean {
        if (fileOrDirectory.isDirectory) {
            val children = fileOrDirectory.listFiles()
            if (children != null) {
                for (child in children) {
                    deleteRecursive(child)
                }
            }
        }
        return fileOrDirectory.delete()
    }
}
