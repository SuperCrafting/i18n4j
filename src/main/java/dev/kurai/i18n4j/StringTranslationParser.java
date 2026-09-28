package dev.kurai.i18n4j;

final class StringTranslationParser implements TranslationParser<String> {

  public static final StringTranslationParser INSTANCE = new StringTranslationParser();

  private StringTranslationParser() {
  }

  @Override
  public String parse(Translation translation, TranslationArgument... arguments) {
    String result = translation.content();

    for (final TranslationArgument argument : arguments) {
      result = result.replace('{' + argument.key() + '}', String.valueOf(argument.value()));
    }

    return result;
  }
}
