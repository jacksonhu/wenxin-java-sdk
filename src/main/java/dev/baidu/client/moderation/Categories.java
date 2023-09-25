package dev.baidu.client.moderation;

import com.google.gson.annotations.SerializedName;
import java.util.Objects;

public final class Categories {
    private final Boolean hate;
    @SerializedName("hate/threatening")
    private final Boolean hateThreatening;
    @SerializedName("self-harm")
    private final Boolean selfHarm;
    private final Boolean sexual;
    @SerializedName("sexual/minors")
    private final Boolean sexualMinors;
    private final Boolean violence;
    @SerializedName("violence/graphic")
    private final Boolean violenceGraphic;

    private Categories(Builder builder) {
        this.hate = builder.hate;
        this.hateThreatening = builder.hateThreatening;
        this.selfHarm = builder.selfHarm;
        this.sexual = builder.sexual;
        this.sexualMinors = builder.sexualMinors;
        this.violence = builder.violence;
        this.violenceGraphic = builder.violenceGraphic;
    }

    public Boolean hate() {
        return this.hate;
    }

    public Boolean hateThreatening() {
        return this.hateThreatening;
    }

    public Boolean selfHarm() {
        return this.selfHarm;
    }

    public Boolean sexual() {
        return this.sexual;
    }

    public Boolean sexualMinors() {
        return this.sexualMinors;
    }

    public Boolean violence() {
        return this.violence;
    }

    public Boolean violenceGraphic() {
        return this.violenceGraphic;
    }

    public boolean equals(Object another) {
        if (this == another) {
            return true;
        } else {
            return another instanceof Categories
                    && this.equalTo((Categories)another);
        }
    }

    private boolean equalTo(Categories another) {
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
        return "Categories{hate=" + this.hate + ", hateThreatening=" + this.hateThreatening + ", selfHarm=" + this.selfHarm + ", sexual=" + this.sexual + ", sexualMinors=" + this.sexualMinors + ", violence=" + this.violence + ", violenceGraphic=" + this.violenceGraphic + "}";
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private Boolean hate;
        private Boolean hateThreatening;
        private Boolean selfHarm;
        private Boolean sexual;
        private Boolean sexualMinors;
        private Boolean violence;
        private Boolean violenceGraphic;

        private Builder() {
        }

        public Builder hate(Boolean hate) {
            this.hate = hate;
            return this;
        }

        public Builder hateThreatening(Boolean hateThreatening) {
            this.hateThreatening = hateThreatening;
            return this;
        }

        public Builder selfHarm(Boolean selfHarm) {
            this.selfHarm = selfHarm;
            return this;
        }

        public Builder sexual(Boolean sexual) {
            this.sexual = sexual;
            return this;
        }

        public Builder sexualMinors(Boolean sexualMinors) {
            this.sexualMinors = sexualMinors;
            return this;
        }

        public Builder violence(Boolean violence) {
            this.violence = violence;
            return this;
        }

        public Builder violenceGraphic(Boolean violenceGraphic) {
            this.violenceGraphic = violenceGraphic;
            return this;
        }

        public Categories build() {
            return new Categories(this);
        }
    }
}
