
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name = "my_table")
public class MyTable implements Serializable {
   private static final long serialVersionUID = 1L;

   @Id
   @Column(name = "id", nullable = false)
   private long m_id;

   @Version
   @Column(name = "optimistic_lock", nullable = false)
   private short m_optimisticLock;

   public MyTable() {
      // nothing to do
   }
}
        