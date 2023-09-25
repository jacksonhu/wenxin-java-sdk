package dev.baidu.client.moderation;

import com.google.gson.annotations.SerializedName;
import java.util.Objects;

public  final class CategoryScores {
    private final Double hate;
    @SerializedName("hate/threatening")
    private final Double hateThreatening;
    @SerializedName("self-harm")
    private final Double selfHarm;
    private final Double sexual;
    @SerializedName("sexual/minors")
    private final Double sexualMinors;
    private final Double violence;
    @SerializedName("violence/graphic")
    private final Double violenceGraphic;

    private CategoryScores(Builder builder) {
        this.hate = builder.hate;
        this.hateThreatening = builder.hateThreatening;
        this.selfHarm = builder.selfHarm;
        this.sexual = builder.sexual;
        this.sexualMinors = builder.sexualMinors;
        this.violence = builder.violence;
        this.violenceGraphic = builder.violenceGraphic;
    }

    public Double hate() {
        return this.hate;
    }

    public Double hateThreatening() {
        return this.hateThreatening;
    }

    public Double selfHarm() {
        return this.selfHarm;
    }

    public Double sexual() {
        return this.sexual;
    }

    public Double sexualMinors() {
        return this.sexualMinors;
    }

    public Double violence() {
        return this.violence;
    }

    public Double violenceGraphic() {
        return this.violenceGraphic;
    }

    public boolean equals(Object another) {
        if (this == another) {
            return true;
        } else {
            return another instanceof CategoryScores
                    && this.equalTo((CategoryScores)another);
        }
    }

    private boolean equalTo(CategoryScores another) {
        return Objects.equals(this.hate, another.hate) && Objects.equals(this.hateThreatening, another.hateThreatening) && Objects.equals(this.selfHarm, another.selfHarm) && Objects.equals(this.sexual, another.sexual) && Objects.equals(this.sexualMinors, another.sexualMinors) && Objects.equals(this.violence, another.violence) && Objects.equals(this.violenceGraphic, another.violenceGraphic);
    }

    public int hashCode() {
        int h = 5381;
        h += (h << 5) + Objects.hashCode(this.hate);
        h += (h << 5) + Objects.hashCode(this.hateThreatening);
        h += (h << 5) + Objects.hashCode(this.selfHarm);
        h += (h << 5) + Objects.hashCode(this.sexual);
        h += (h << 5) + Objects.hashCode(this.sexualMinors);
        h += (h << 5) + Objects.hashCode(this.violence);
        h += (h << 5) + Objects.hashCode(this.violenceGraphic);
        return h;
    }

    public String toString() {
        return "CategoryScores{hate=" + this.hate + ", hateThreatening=" + this.hateThreatening + ", selfHarm=" + this.selfHarm + ", sexual=" + this.sexual + ", sexualMinors=" + this.sexualMinors + ", violence=" + this.violence + ", violenceGraphic=" + this.violenceGraphic + "}";
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private Double hate;
        private Double hateThreatening;
        private Double selfHarm;
        private Double sexual;
        private Double sexualMinors;
        private Double violence;
        private Double violenceGraphic;

        private Builder() {
        }

        public Builder hate(Double hate) {
            this.hate = hate;
            return this;
        }

        public Builder hateThreatening(Double hateThreatening) {
            this.hateThreatening = hateThreatening;
            return this;
        }

        public Builder selfHarm(Double selfHarm) {
            this.selfHarm = selfHarm;
            return this;
        }

        public Builder sexual(Double sexual) {
            this.sexual = sexual;
            return this;
        }

        public Builder sexualMinors(Double sexualMinors) {
            this.sexualMinors = sexualMinors;
            return this;
        }

        public Builder violence(Double violence) {
            this.violence = violence;
            return this;
        }

        public Builder violenceGraphic(Double violenceGraphic) {
            this.violenceGraphic = violenceGraphic;
            return this;
        }

        public CategoryScores build() {
            return new CategoryScores(this);
        }
    }
}
