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
package de.danielnaber.languagetool.language;

import java.util.*;

import de.danielnaber.languagetool.Language;
import de.danielnaber.languagetool.rules.*;
import de.danielnaber.languagetool.rules.en.AvsAnRule;
import de.danielnaber.languagetool.rules.en.CompoundRule;
import de.danielnaber.languagetool.rules.en.EnglishUnpairedBracketsRule;
import de.danielnaber.languagetool.synthesis.Synthesizer;
import de.danielnaber.languagetool.synthesis.en.EnglishSynthesizer;
import de.danielnaber.languagetool.tagging.Tagger;
import de.danielnaber.languagetool.tagging.disambiguation.Disambiguator;
import de.danielnaber.languagetool.tagging.disambiguation.rules.en.EnglishRuleDisambiguator;
import de.danielnaber.languagetool.tagging.en.EnglishTagger;
import de.danielnaber.languagetool.tokenizers.SRXSentenceTokenizer;
import de.danielnaber.languagetool.tokenizers.SentenceTokenizer;
import de.danielnaber.languagetool.tokenizers.Tokenizer;
import de.danielnaber.languagetool.tokenizers.en.EnglishWordTokenizer;

public class English extends Language {

  private final Tagger tagger = new EnglishTagger();
  private final Tokenizer wordTokenizer = new EnglishWordTokenizer();
  private final SentenceTokenizer sentenceTokenizer = new SRXSentenceTokenizer("en");
  private final Synthesizer synthesizer = new EnglishSynthesizer();
  private final Disambiguator disambiguator = new EnglishRuleDisambiguator();

  public final Locale getLocale() {
    return new Locale(getShortName());
  }

  public final SentenceTokenizer getSentenceTokenizer() {
    return sentenceTokenizer;
  }
  
  public final String getName() {
    return "English";
  }

  public final String getShortName() {
    return "en";
  }

  public final String[] getCountryVariants() {
    return new String[]{"GB", "US", "AU", "CA", "NZ", "ZA"};
  }
  
  public final Tagger getTagger() {
    return tagger;
  }

  public final Tokenizer getWordTokenizer() {
    return wordTokenizer;
  }

  public final Synthesizer getSynthesizer() {
    return synthesizer;
  }
  
  public final Disambiguator getDisambiguator() {
    return disambiguator;
  }

  
  public final Contributor[] getMaintainers() {
    return new Contributor[] {new Contributor("Marcin Miłkowski"),
        new Contributor("Daniel Naber")};
  }

  @Override
  public List<Class<? extends Rule>> getRelevantRules() {
    return Arrays.asList(
            CommaWhitespaceRule.class,
            DoublePunctuationRule.class,
            EnglishUnpairedBracketsRule.class,
            UppercaseSentenceStartRule.class,
            WhitespaceRule.class,
            WordRepeatRule.class,
            // specific to English:
            AvsAnRule.class,
            CompoundRule.class
    );
  }

}
