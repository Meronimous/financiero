package com.contaduria.movimientofinanciero.specifications;

import com.contaduria.movimientofinanciero.entities.AdministrativeDocument;
import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.domain.LikeIgnoreCase;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Join;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Joins;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.springframework.data.jpa.domain.Specification;
@And({
        @Spec(path ="number", spec = Equal.class),
        @Spec(path ="year", spec = Equal.class),
        @Spec(path ="codeOrganism", spec = Equal.class),
        @Spec(path ="description", spec = LikeIgnoreCase.class)}
)
public interface AdministrativeDocumentSpecification extends Specification<AdministrativeDocument>  {
    public static Specification<AdministrativeDocument> documentnumber(Integer number){
        return (number == null ) ? null : (administrativeDocument, cq, cb) -> cb.equal(administrativeDocument.get("number"), number);
    }
    public static Specification<AdministrativeDocument> documentCodeOrganism(Integer codeOrganism){
        return (codeOrganism == null ) ? null : (administrativeDocument, cq, cb) -> cb.equal(administrativeDocument.get("codeOrganism"), codeOrganism);
    }
    public static Specification<AdministrativeDocument> documentYear(Integer year){
        return (year == null ) ? null : (administrativeDocument, cq, cb) -> cb.equal(administrativeDocument.get("year"), year);
    }
}