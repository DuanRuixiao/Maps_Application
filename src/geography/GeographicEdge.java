package geography;

public class GeographicEdge {
    private GeographicPoint startPoint;
    private GeographicPoint endPoint;
    private String roadName;
    private String roadType;
    private double length;

    public GeographicEdge(GeographicPoint startPoint, GeographicPoint endPoint, String roadName, String roadType,
                          double length) {
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.roadName = roadName;
        this.roadType = roadType;
        this.length = length;
    }

    public GeographicPoint getStartPoint() {
        return startPoint;
    }

    public GeographicPoint getEndPoint() {
        return endPoint;
    }

    public String getRoadName() {
        return roadName;
    }

    public String getRoadType() {
        return roadType;
    }

    public double getLength() {
        return length;
    }
}
