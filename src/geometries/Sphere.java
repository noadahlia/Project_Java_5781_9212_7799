package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static primitives.Util.alignZero;

public class Sphere implements Geometry {
    Point3D center;
    double radius;

    @Override
    public Vector getNormal(Point3D p)
    {
        Vector n = new Vector(p.subtract(center));
        return n.normalize();
    }

    public Sphere(Point3D center, double radius) {
        this.center = center;
        this.radius = radius;
    }

    public Point3D getCenter() {
        return center;
    }

    public double getRadius() {
        return radius;
    }

    /**
     * @param ray
     * @return List of intersectable Point3D if they exist or null
     */
    @Override
    public List<Point3D> findIntersections(Ray ray) {
            Point3D P0 = ray.getP0();
            Vector v = ray.getDir();
            //Checking if the ray start in the center of the sphere and returning the ray point
            if (P0.equals(getCenter())) {
                return List.of(getCenter().add(v.scale(getRadius())));
            }
            //Calculating the distance between P0 and the center
            Vector U = getCenter().subtract(P0);
            //tm=v*U
            double tm = alignZero(v.dotProduct(U));
            //d= the distance between U and tm
            double d = alignZero(Math.sqrt(U.lengthSquared() - tm * tm));

            // no intersections : the ray direction is above the sphere
            if (d >= getRadius()) {
                return null;
            }

            double th = alignZero(Math.sqrt(getRadius() * getRadius() - d * d));
            //Calculating the distance of the ray
            double t1 = alignZero(tm - th);
            double t2 = alignZero(tm + th);
            // We take only t>0
            if (t1 > 0 && t2 > 0) {
                Point3D P1 = P0.add(v.scale(t1));
                Point3D P2 = P0.add(v.scale(t2));
                return List.of(P1, P2);
            }
            //if t1>0 and t2<=0
            if (t1 > 0) {
                Point3D P1 = P0.add(v.scale(t1));
                return List.of(P1);
            }
            //if t2>0 and t1<=0
            if (t2 > 0) {
                Point3D P2 = P0.add(v.scale(t2));
                return List.of(P2);
            }
            return null;
        }



    @Override
    public String toString() {
        return "Sphere{" +
                "center=" + center +
                ", radius=" + radius +
                '}';
    }
}
