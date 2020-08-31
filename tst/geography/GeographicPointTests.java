package geography;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GeographicPointTests {
    private GeographicPoint geographicPoint;

    public GeographicPointTests() {
        double latitude = 22.34;
        double longitude = 56.98;
        geographicPoint = new GeographicPoint(latitude, longitude);
    }

    @Test
    public void distanceTest() {
        double latitude = 45.55;
        double longitude = 87.23;
        GeographicPoint other = new GeographicPoint(latitude, longitude);
        assertEquals(3760.88, geographicPoint.distance(other), 1.0);
    }
}
