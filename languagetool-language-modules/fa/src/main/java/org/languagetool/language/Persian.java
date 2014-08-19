/* LanguageTool, a natural language style checker 
 * Copyright (C) 2014 Daniel Naber (http://www.danielnaber.de)
 * 
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301
 * USA
 */
package org.languagetool.language;

import org.languagetool.Language;
import org.languagetool.rules.*;
import org.languagetool.tokenizers.SRXSentenceTokenizer;
import org.languagetool.tokenizers.SentenceTokenizer;

import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class Persian extends Language {

  private SentenceTokenizer sentenceTokenizer;
  private String name = "Persian";

  @Override
  public String getName() {
    return name;
  }

  @Override
  public void setName(final String name) {
    this.name = name;
  }

  @Override
  public final String getShortName() {
    return "fa";
  }

  @Override
  public final String[] getCountries() {
    return new String[]{"IR", "AF"};
  }
  
  @Override
  public SentenceTokenizer getSentenceTokenizer() {
    if (sentenceTokenizer == null) {
      sentenceTokenizer = new SRXSentenceTokenizer(this);
    }
    return sentenceTokenizer;
  }

  @Override
  public final Contributor[] getMaintainers() {
    return new Contributor[] {new Contributor("Reza1615")};
  }

  @Override
  public List<Rule> getRelevantRules(ResourceBundle messages) {
    return Arrays.asList(
        //new CommaWhitespaceRule(messages),
        //new DoublePunctuationRule(messages),
        //new MultipleWhitespaceRule(messages, this),
        //new LongSentenceRule(messages),
        //new SentenceWhitespaceRule(messages)
    );
  }

}