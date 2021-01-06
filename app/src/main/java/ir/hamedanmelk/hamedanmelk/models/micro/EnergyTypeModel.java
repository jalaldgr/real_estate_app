package ir.hamedanmelk.hamedanmelk.models.micro;

public class EnergyTypeModel {

    private String land_id;
    private String energy_type_id;
    private String energy_state_id;
    private String EnergyTypeTitle;
    private String EnergyStateTitle;

    public EnergyTypeModel(String land_id, String energy_type_id, String energy_state_id, String energyTypeTitle, String energyStateTitle) {
        this.land_id = land_id;
        this.energy_type_id = energy_type_id;
        this.energy_state_id = energy_state_id;
        EnergyTypeTitle = energyTypeTitle;
        EnergyStateTitle = energyStateTitle;
    }

    public String getLand_id() {
        return land_id;
    }

    public void setLand_id(String land_id) {
        this.land_id = land_id;
    }

    public String getEnergy_type_id() {
        return energy_type_id;
    }

    public void setEnergy_type_id(String energy_type_id) {
        this.energy_type_id = energy_type_id;
    }

    public String getEnergy_state_id() {
        return energy_state_id;
    }

    public void setEnergy_state_id(String energy_state_id) {
        this.energy_state_id = energy_state_id;
    }

    public String getEnergyTypeTitle() {
        return EnergyTypeTitle;
    }

    public void setEnergyTypeTitle(String energyTypeTitle) {
        EnergyTypeTitle = energyTypeTitle;
    }

    public String getEnergyStateTitle() {
        return EnergyStateTitle;
    }

    public void setEnergyStateTitle(String energyStateTitle) {
        EnergyStateTitle = energyStateTitle;
    }
}
