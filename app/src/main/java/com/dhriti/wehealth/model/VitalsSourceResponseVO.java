package com.dhriti.wehealth.model;

public class VitalsSourceResponseVO {

    private VitalsSources[] vitalsSources;

    private String vitalsSourcesTitle;

    public VitalsSources[] getVitalsSources() {
        return vitalsSources;
    }

    public void setVitalsSources(VitalsSources[] vitalsSources) {
        this.vitalsSources = vitalsSources;
    }

    public String getVitalsSourcesTitle() {
        return vitalsSourcesTitle;
    }

    public void setVitalsSourcesTitle(String vitalsSourcesTitle) {
        this.vitalsSourcesTitle = vitalsSourcesTitle;
    }

    public class VitalsSources {

        private String vitalSourceName;

        public String getVitalSourceName() {
            return vitalSourceName;
        }

        public void setVitalSourceName(String vitalSourceName) {
            this.vitalSourceName = vitalSourceName;
        }
    }
}
