package com.contaduria.movimientofinanciero.specifications;

import com.contaduria.movimientofinanciero.entities.AdministrativeDocument;
import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.domain.LikeIgnoreCase;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.springframework.data.jpa.domain.Specification;

@And({
        @Spec(path ="number", spec = Equal.class),
        @Spec(path ="year", spec = Equal.class),
        @Spec(path ="codOrganism", spec = Equal.class),
        @Spec(path ="description", spec = LikeIgnoreCase.class)}
)
public interface AdministrativeDocumentSpecification extends Specification<AdministrativeDocument>  {
}