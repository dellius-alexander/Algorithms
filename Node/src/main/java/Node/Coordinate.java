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
import java.io.Serializable;
import java.util.Objects;
/////////////////////////////////////////////////////////////////////
/**
 * Represents the node coordinates.
 * @param <Row> the row
 * @param <Column> the column
 */
public class Coordinate<Row,Column> implements Serializable
{
    /**
     * row
     */
    private Row row;
    /**
     * column
     */
    private Column column;

    public Coordinate() {
        this.row = null;
        this.column = null;
    }
    /**
     * Coordinates of the node
     * @param row row
     * @param column column
     */
    public Coordinate(Row row, Column column) {
        this.row = row;
        this.column = column;
    }

    /**
     * get the row
     * @return the row
     */
    public Row getRow() {
        return this.row;
    }

    /**
     * get the column
     * @return the column
     */
    public Column getColumn() {
        return this.column;
    }

    /**
     * set the row
     * @param row the row
     */
    public void setRow(Row row) {
        this.row = row;
    }

    /**
     * set the column
     * @param column the column
     */
    public void setColumn(Column column) {
        this.column = column;
    }

    /**
     * Check if object is equal to this one.
     * @param o the object to test
     * @return true if equal, otherwise false
     */
    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Coordinate)) return false;
        final Coordinate<?, ?> other = (Coordinate<?, ?>) o;
        if (!other.canEqual( this)) return false;
        final Object this$row = this.getRow();
        final Object other$row = other.getRow();
        if (!Objects.equals(this$row, other$row)) return false;
        final Object this$column = this.getColumn();
        final Object other$column = other.getColumn();
        return Objects.equals(this$column, other$column);
    }

    /**
     * Check if object is instance of this one.
     * @param other the object
     * @return true if is instance, otherwise false
     */
    protected boolean canEqual(final Object other) {
        return other instanceof Coordinate;
    }

    /**
     * The hashcode
     * @return the hashcode
     */
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $row = this.getRow();
        result = result * PRIME + ($row == null ? 43 : $row.hashCode());
        final Object $column = this.getColumn();
        result = result * PRIME + ($column == null ? 43 : $column.hashCode());
        return result;
    }

    /**
     * To string
     * @return the contents of this class
     */
    public String toString() {

        return "Coordinate{" +
                "\n\t\"row\":" + this.getRow() + "," +
                "\n\t\"column\":" + this.getColumn() +
                "\n\t}";
    }
}
