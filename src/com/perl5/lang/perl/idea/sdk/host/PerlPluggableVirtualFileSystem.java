/*
 * Copyright 2015-2018 Alexandr Evstigneev
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.perl5.lang.perl.idea.sdk.host;

import com.intellij.openapi.vfs.DeprecatedVirtualFileSystem;
import com.intellij.openapi.vfs.VirtualFileSystem;
import com.intellij.testFramework.LightVirtualFile;
import org.jetbrains.annotations.NotNull;

public abstract class PerlPluggableVirtualFileSystem extends DeprecatedVirtualFileSystem {
  /**
   * Should clean the data if necessary
   */
  public void clean() {}

  @Override
  public void refresh(boolean asynchronous) {
  }

  @NotNull
  @Override
  public final String getProtocol() {
    throw new RuntimeException("This should neve be called, PerlHostVirtualFileSystem is a frontend");
  }

  protected abstract class PerlPluggableVirtualFile extends LightVirtualFile {
    @Override
    public boolean isWritable() {
      return false;
    }

    @NotNull
    @Override
    public final VirtualFileSystem getFileSystem() {
      return PerlHostVirtualFileSystem.getInstance();
    }
  }
}
