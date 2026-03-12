package j4q.geometry;

public class Vector3 {

    public float x;
    public float y;
    public float z;

    // Constructors
    public Vector3() {
        this(0,0,0);
    }

    public Vector3(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vector3(Vector3 v){
        this(v.x, v.y, v.z);
    }

    // Set
    public Vector3 set(float x, float y, float z){
        this.x = x;
        this.y = y;
        this.z = z;
        return this;
    }

    public Vector3 set(Vector3 v){
        return set(v.x, v.y, v.z);
    }

    // Add
    public Vector3 add(Vector3 v){
        x += v.x;
        y += v.y;
        z += v.z;
        return this;
    }

    public Vector3 add(float x, float y, float z){
        this.x += x;
        this.y += y;
        this.z += z;
        return this;
    }

    // Subtract
    public Vector3 subtract(Vector3 v){
        x -= v.x;
        y -= v.y;
        z -= v.z;
        return this;
    }

    // Scale
    public Vector3 scale(float s){
        x *= s;
        y *= s;
        z *= s;
        return this;
    }

    // Dot product
    public float dot(Vector3 v){
        return x*v.x + y*v.y + z*v.z;
    }

    // Cross product
    public Vector3 cross(Vector3 v){
        float cx = y*v.z - z*v.y;
        float cy = z*v.x - x*v.z;
        float cz = x*v.y - y*v.x;
        return set(cx, cy, cz);
    }

    // Length
    public float length(){
        return (float)Math.sqrt(x*x + y*y + z*z);
    }

    public float lengthSquared(){
        return x*x + y*y + z*z;
    }

    // Normalize
    public Vector3 normalize(){
        float len = length();
        if(len != 0){
            scale(1f/len);
        }
        return this;
    }

    // Distance
    public float distance(Vector3 p){
        float dx = x - p.x;
        float dy = y - p.y;
        float dz = z - p.z;
        return (float)Math.sqrt(dx*dx + dy*dy + dz*dz);
    }

    // Copy
    public Vector3 copy(){
        return new Vector3(x,y,z);
    }

    // Zero
    public Vector3 zero(){
        x = y = z = 0;
        return this;
    }

    // String
    @Override
    public String toString(){
        return "(" + x + ", " + y + ", " + z + ")";
    }
}
