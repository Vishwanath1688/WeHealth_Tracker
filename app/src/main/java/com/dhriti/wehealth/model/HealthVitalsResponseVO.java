package com.dhriti.wehealth.model;

public class HealthVitalsResponseVO {

    private String healthVitalsTitle;

    private HealthVitals[] healthVitals;

    public String getHealthVitalsTitle() {
        return healthVitalsTitle;
    }

    public void setHealthVitalsTitle(String healthVitalsTitle) {
        this.healthVitalsTitle = healthVitalsTitle;
    }

    public HealthVitals[] getHealthVitals() {
        return healthVitals;
    }

    public void setHealthVitals(HealthVitals[] healthVitals) {
        this.healthVitals = healthVitals;
    }

    public class HealthVitals {

        private String vitalValue;

        private String vitalName;

        private String vitalSource;

        private String vitalDayAndTime;

        public String getVitalValue() {
            return vitalValue;
        }

        public void setVitalValue(String vitalValue) {
            this.vitalValue = vitalValue;
        }

        public String getVitalName() {
            return vitalName;
        }

        public void setVitalName(String vitalName) {
            this.vitalName = vitalName;
        }

        public String getVitalSource() {
            return vitalSource;
        }

        public void setVitalSource(String vitalSource) {
            this.vitalSource = vitalSource;
        }

        public String getVitalDayAndTime() {
            return vitalDayAndTime;
        }

        public void setVitalDayAndTime(String vitalDayAndTime) {
            this.vitalDayAndTime = vitalDayAndTime;
        }

    }
}
