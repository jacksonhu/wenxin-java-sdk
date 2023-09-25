package dev.baidu.client;

public enum Model {
    TEXT_DAVINCI_003("text-davinci-003"),
    GPT_3_5_TURBO("gpt-3.5-turbo"),
    GPT_4("gpt-4"),
    TEXT_EMBEDDING_ADA_002("text-embedding-ada-002"),
    TEXT_MODERATION_STABLE("text-moderation-stable"),
    TEXT_MODERATION_LATEST("text-moderation-latest");

    private final String stringValue;

    private Model(String stringValue) {
        this.stringValue = stringValue;
    }

    public String stringValue() {
        return this.stringValue;
    }
}
