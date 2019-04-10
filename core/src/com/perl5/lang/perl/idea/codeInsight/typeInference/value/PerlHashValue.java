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

package com.perl5.lang.perl.idea.codeInsight.typeInference.value;

import com.intellij.psi.stubs.StubInputStream;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.List;

public class PerlHashValue extends PerlMapValue {
  public PerlHashValue(@NotNull List<PerlValue> elements) {
    super(elements);
  }

  public PerlHashValue(@NotNull StubInputStream dataStream) throws IOException {
    super(dataStream);
  }

  @Override
  protected int getSerializationId() {
    return PerlValuesManager.HASH_ID;
  }

  @Override
  protected String getValuesPresentableText() {
    List<PerlValue> elements = getElements();
    StringBuilder sb = new StringBuilder();
    for (int i = 0, size = elements.size(); i < size; i++) {
      if (sb.length() > 0) {
        sb.append(",\n");
      }
      sb.append(elements.get(i++).getPresentableText()).append(" => ").append(elements.get(i));
    }
    return sb.toString();
  }
}
