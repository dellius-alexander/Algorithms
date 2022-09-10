/**
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 *
 * Algorithms  Copyright (C) 2022  Dellius Alexander
 *
 * This program comes with ABSOLUTELY NO WARRANTY; for details type `show w'.
 * This is free software, and you are welcome to redistribute it
 * under certain conditions; type `show c' for details.
 */
/////////////////////////////////////////////////////////////////////
package Node;
/////////////////////////////////////////////////////////////////////
import java.util.Objects;
/////////////////////////////////////////////////////////////////////
/**
 * The cost or weight of traveling between edges.
 * @param <Metric> the Metrix of the weight.
 */
public class Distance<Metric>
{

    /**
     * The Metrix of the distance
     */
    Metric value;

    public Distance(){this.value = null;}
//    public Distance(Metrix value){ this.value = value; }
//    public Distance(Metrix value){ this.value = value; }

    public Distance(Metric value){ this.value =  value; }
//    public Distance(Integer value){ this.value = (Metrix) value; }

    /**
     * Represents the weight/cost/distance between two nodes.
     * @return the weight/cost/distance between two nodes.
     */
    public Metric getValue() {
        return value;
    }

    /**
     * Sets the weight/cost/distance between two nodes.
     * @param value the weight/cost/distance between two nodes.
     */
    public void setValue(Metric value) {
        this.value = value;
    }
    /**
     * If object o is equal to this class object.
     * @param o the object to compare
     * @return true if equal otherwise false
     */
    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Distance)) return false;
        final Distance<?> other = (Distance<?>) o;
        if (!other.canEqual( this)) return false;
        final Object this$value = this.getValue();
        final Object other$value = other.getValue();
        return Objects.equals(this$value, other$value);
    }
    /**
     * Is this other object an instance of this object.
     * @param other the object to compare
     * @return true if an instance of this object, false otherwise
     */
    protected boolean canEqual(final Object other) {
        return other instanceof Distance;
    }
    /**
     * Get the hashcode of this class
     * @return the hashcode
     */
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $value = this.getValue();
        result = result * PRIME + ($value == null ? 43 : $value.hashCode());
        return result;
    }
    /**
     * To string
     * @return the contents of this object
     */
    public String toString() {
        return "Distance{\n\t\"Distance\":" + this.getValue() + "\n\t}";
    }
}