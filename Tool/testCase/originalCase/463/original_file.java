
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.Column;
@Entity
@Table(name = "my_table")
public class MyTable implements Serializable
{
   private static final long serialVersionUID = 1L;

   @Id
   @Column(name = "directory", nullable = false)
   private long m_id;  // PMD Violation: ImmutableField

   // other fields

   /**
    * For JPA
    */
   public MyTable()
   {
      // nothing to do
   }

   public long getId()
   {
      return m_id;
   }
}
