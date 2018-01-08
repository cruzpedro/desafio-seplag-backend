package br.com.seplag.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

/**
 * Classe reponsável por representar toda e qualquer entidade no sistema.
 * Ela encapsula o campo base de todas as entidades, que no caso é o <strong>ID</strong>, e o <strong>STATUS</strong>.
 *
 * @param <I> O tipo da chave primária da entidade que você quer criar.
 */
@MappedSuperclass
public abstract class BaseEntity<I extends Serializable> implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private I id;

    /**
     * Método responsável por recuperar o ID da entidade no sistema
     *
     * @return O ID da entidade
     */
    public I getId() {
        return id;
    }

    public void setId(I id) {
		this.id = id;
	}

	@Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        BaseEntity that = (BaseEntity) o;

        return !(id != null ? !id.equals(that.id) : that.id != null);
    }
}
