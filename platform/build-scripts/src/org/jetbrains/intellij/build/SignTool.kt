// Copyright 2000-2021 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
package org.jetbrains.intellij.build

import java.nio.file.Path

/**
 * Implement this interfaces and pass the implementation to {@link ProprietaryBuildTools} constructor to sign the product's files.
 */
interface SignTool {
  companion object {
    const val LIB_VERSION_OPTION_NAME: String = "libVersion"
  }

  val usePresignedNativeFiles: Boolean

  suspend fun signFiles(files: List<Path>, context: BuildContext, options: Map<String, String>)

  /**
   * Returns `null` if failed to download and error is not fatal.
   */
  suspend fun getPresignedLibraryFile(path: String, libName: String, libVersion: String, context: BuildContext): Path?

  fun commandLineClient(context: BuildContext, os: OsFamily, arch: JvmArchitecture): Path?
}