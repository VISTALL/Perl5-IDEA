/*
 * Copyright 2015-2020 Alexandr Evstigneev
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

package com.perl5.lang.perl.psi.stubs.imports;

import com.intellij.psi.stubs.StubIndexExtension;
import com.intellij.psi.stubs.StubIndexKey;
import com.perl5.lang.perl.psi.impl.PerlUseStatementElement;
import com.perl5.lang.perl.psi.stubs.PerlStubIndexBase;
import org.jetbrains.annotations.NotNull;

/**
 * Indexes use statements by context namespace
 */
public class PerlUseStatementsIndex extends PerlStubIndexBase<PerlUseStatementElement> {
  public static final int VERSION = 1;
  public static final StubIndexKey<String, PerlUseStatementElement> KEY = StubIndexKey.createIndexKey("perl.use.statements");

  @Override
  public int getVersion() {
    return super.getVersion() + VERSION;
  }

  @Override
  public @NotNull StubIndexKey<String, PerlUseStatementElement> getKey() {
    return KEY;
  }

  @Override
  protected @NotNull Class<PerlUseStatementElement> getPsiClass() {
    return PerlUseStatementElement.class;
  }

  public static @NotNull PerlUseStatementsIndex getInstance() {
    return StubIndexExtension.EP_NAME.findExtensionOrFail(PerlUseStatementsIndex.class);
  }
}
