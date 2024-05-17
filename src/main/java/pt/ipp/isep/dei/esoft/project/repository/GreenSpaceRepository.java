package pt.ipp.isep.dei.esoft.project.repository;
import pt.ipp.isep.dei.esoft.project.domain.*;
import java.util.ArrayList;
import java.util.List;

public class GreenSpaceRepository {
        private List<GreenSpace> greenSpaces;

        public GreenSpaceRepository() {
            this.greenSpaces = new ArrayList<>();
        }

        public boolean registerGreenSpace(GreenSpace greenSpace) {
            if (validateGreenSpace(greenSpace)) {
                saveGreenSpace(greenSpace);
                return true;
            } else {
                return false;
            }
        }

        private boolean validateGreenSpace(GreenSpace greenSpace) {
            // Add validation logic
            return greenSpace.getName() != null && !greenSpace.getName().isEmpty() &&
                    greenSpace.getLocationCoordinates() > 0 &&
                    greenSpace.getArea() > 0;
        }

        private void saveGreenSpace(GreenSpace greenSpace) {
            greenSpaces.add(greenSpace);
        }
}
