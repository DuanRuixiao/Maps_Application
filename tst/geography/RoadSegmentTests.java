package geography;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import java.util.LinkedList;
import java.util.List;

public class RoadSegmentTests {
    private RoadSegment roadSegment;

    public RoadSegmentTests() {
        GeographicPoint pt1 = new GeographicPoint(0, 0);
        GeographicPoint pt2 = new GeographicPoint(5,5);
        List<GeographicPoint> geometry = new LinkedList<GeographicPoint>(){{
            add(new GeographicPoint(1,1));
            add(new GeographicPoint(2,2));
            add(new GeographicPoint(3,3));
            add(new GeographicPoint(4,4));
        }};
        String roadName = "dummyRoadName";
        String roadType = "dummyRoadType";
        double length = 3.14;

        roadSegment = new RoadSegment(pt1, pt2, geometry, roadName, roadType, length);
    }

    @Test
    public void equalsTest() {
        GeographicPoint otherPt1 = new GeographicPoint(0, 0);
        GeographicPoint otherPt2 = new GeographicPoint(5,5);
        List<GeographicPoint> otherGeometry1 = new LinkedList<GeographicPoint>(){{
            add(new GeographicPoint(1,1));
            add(new GeographicPoint(2,2));
            add(new GeographicPoint(3,3));
            add(new GeographicPoint(4,4));
        }};
        List<GeographicPoint> otherGeometry2 = new LinkedList<GeographicPoint>(){{
            add(new GeographicPoint(1,1));
            add(new GeographicPoint(2,2));
        }};
        String otherRoadName = "dummyRoadName";
        String otherRoadType = "dummyRoadType";
        double otherLength1 = 3.14;
        double otherLength2 = 10.0;

        RoadSegment otherRoadSegment1 = new RoadSegment(otherPt1, otherPt2, otherGeometry1, otherRoadName, otherRoadType,
                otherLength1);
        RoadSegment otherRoadSegment2 = new RoadSegment(otherPt1, otherPt2, otherGeometry2, otherRoadName, otherRoadType,
                otherLength1);
        RoadSegment otherRoadSegment3 = new RoadSegment(otherPt1, otherPt2, otherGeometry1, otherRoadName, otherRoadType,
                otherLength2);

        assertTrue(roadSegment.equals(otherRoadSegment1));
        assertTrue(roadSegment.equals(otherRoadSegment2));
        assertFalse(roadSegment.equals(otherRoadSegment3));
    }

    @Test
    public void getOtherPointTest() {
        GeographicPoint pt1 = new GeographicPoint(0, 0);
        assertEquals(new GeographicPoint(5,5), roadSegment.getOtherPoint(pt1));
    }
}
