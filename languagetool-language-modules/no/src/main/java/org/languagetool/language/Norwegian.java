/* LanguageTool, a natural language style checker
 * Copyright (C) 2007 Daniel Naber (http://www.danielnaber.de)
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

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import org.languagetool.Language;
import org.languagetool.LanguageMaintainedState;
import org.languagetool.chunking.Chunker;
import org.languagetool.languagemodel.LanguageModel;
import org.languagetool.languagemodel.LuceneLanguageModel;
import org.languagetool.rules.*;
import org.languagetool.synthesis.Synthesizer;
import org.languagetool.tagging.Tagger;
import org.languagetool.tagging.disambiguation.Disambiguator;
import org.languagetool.tagging.disambiguation.rules.XmlRuleDisambiguator;
import org.languagetool.tokenizers.SRXSentenceTokenizer;
import org.languagetool.tokenizers.SentenceTokenizer;
import org.languagetool.tokenizers.WordTokenizer;

public class Norwegian extends Language {

  private LuceneLanguageModel languageModel;

  @Override
  public String getName() {
    return "Norwegian";
  }

  @Override
  public String getShortName() {
    return "no";
  }

  @Override
  public String[] getCountries() {
    return new String[]{};
  }

  @Override
  public synchronized LanguageModel getLanguageModel(File indexDir) throws IOException {
    if (languageModel == null) {
      languageModel = new LuceneLanguageModel(new File(indexDir, getShortName()));
    }
    return languageModel;
  }

  @Override
  public Contributor[] getMaintainers() {
    return new Contributor[] { new Contributor("Eirik Birkeland") };
  }

  @Override
  public List<Rule> getRelevantRules(ResourceBundle messages) throws IOException {
    return Arrays.asList(
        new CommaWhitespaceRule(messages,
                Example.wrong("We had coffee<marker> ,</marker> cheese and crackers and grapes."),
                Example.fixed("We had coffee<marker>,</marker> cheese and crackers and grapes.")),
        new DoublePunctuationRule(messages),
        new UppercaseSentenceStartRule(messages, this,
                Example.wrong("This house is old. <marker>it</marker> was built in 1950."),
                Example.fixed("This house is old. <marker>It</marker> was built in 1950.")),
        new MultipleWhitespaceRule(messages, this),
        new LongSentenceRule(messages),
        new SentenceWhitespaceRule(messages)
    );
  }
}
