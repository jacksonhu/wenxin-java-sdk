package dev.baidu.client.moderation;

import java.util.Objects;

public final class ModerationResult {
    private final Categories categories;
    private final CategoryScores categoryScores;
    private final Boolean flagged;

    private ModerationResult(Builder builder) {
        this.categories = builder.categories;
        this.categoryScores = builder.categoryScores;
        this.flagged = builder.flagged;
    }

    public Categories categories() {
        return this.categories;
    }

    public CategoryScores categoryScores() {
        return this.categoryScores;
    }

    public Boolean isFlagged() {
        return this.flagged;
    }

    public boolean equals(Object another) {
        if (this == another) {
            return true;
        } else {
            return another instanceof ModerationResult
                    && this.equalTo((ModerationResult)another);
        }
    }

    private boolean equalTo(ModerationResult another) {
        return Objects.equals(this.categories, another.categories) && Objects.equals(this.categoryScores, another.categoryScores) && Objects.equals(this.flagged, another.flagged);
    }

    public int hashCode() {
        int h = 5381;
        h += (h << 5) + Objects.hashCode(this.categories);
        h += (h << 5) + Objects.hashCode(this.categoryScores);
        h += (h << 5) + Objects.hashCode(this.flagged);
        return h;
    }

    public String toString() {
        return "Moderation{categories=" + this.categories + ", categoryScores=" + this.categoryScores + ", flagged=" + this.flagged + "}";
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private Categories categories;
        private CategoryScores categoryScores;
        private Boolean flagged;

        private Builder() {
        }

        public Builder categories(Categories categories) {
            this.categories = categories;
            return this;
        }

        public Builder categoryScores(CategoryScores categoryScores) {
            this.categoryScores = categoryScores;
            return this;
        }

        public Builder flagged(Boolean flagged) {
            this.flagged = flagged;
            return this;
        }

        public ModerationResult build() {
            return new ModerationResult(this);
        }
    }
}
