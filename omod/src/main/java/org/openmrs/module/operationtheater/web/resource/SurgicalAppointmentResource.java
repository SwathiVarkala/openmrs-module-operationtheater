package org.openmrs.module.operationtheater.web.resource;


import org.openmrs.api.context.Context;
import org.openmrs.module.operationtheater.api.model.SurgicalAppointment;
import org.openmrs.module.operationtheater.api.model.SurgicalBlock;
import org.openmrs.module.operationtheater.api.service.SurgicalAppointmentService;
import org.openmrs.module.operationtheater.api.service.SurgicalBlockService;
import org.openmrs.module.webservices.rest.web.RequestContext;
import org.openmrs.module.webservices.rest.web.RestConstants;
import org.openmrs.module.webservices.rest.web.annotation.Resource;
import org.openmrs.module.webservices.rest.web.representation.DefaultRepresentation;
import org.openmrs.module.webservices.rest.web.representation.FullRepresentation;
import org.openmrs.module.webservices.rest.web.representation.RefRepresentation;
import org.openmrs.module.webservices.rest.web.representation.Representation;
import org.openmrs.module.webservices.rest.web.resource.impl.DataDelegatingCrudResource;
import org.openmrs.module.webservices.rest.web.resource.impl.DelegatingResourceDescription;
import org.openmrs.module.webservices.rest.web.response.ResourceDoesNotSupportOperationException;
import org.openmrs.module.webservices.rest.web.response.ResponseException;

@Resource(name = RestConstants.VERSION_1 + "/surgicalAppointment", supportedClass = SurgicalAppointment.class, supportedOpenmrsVersions = {"2.0.*", "2.1.*"})
public class SurgicalAppointmentResource extends DataDelegatingCrudResource<SurgicalAppointment> {


    @Override
    public SurgicalAppointment getByUniqueId(String s) {
        return null;
    }

    @Override
    protected void delete(SurgicalAppointment surgicalAppointment, String s, RequestContext requestContext) throws ResponseException {
        throw new ResourceDoesNotSupportOperationException("Delete not supported on SurgicalAppointment resource");
    }

    @Override
    public SurgicalAppointment newDelegate() {
        return new SurgicalAppointment();
    }

    @Override
    public SurgicalAppointment save(SurgicalAppointment surgicalAppointment) {
        return Context.getService(SurgicalAppointmentService.class).save(surgicalAppointment);
    }

    @Override
    public void purge(SurgicalAppointment surgicalAppointment, RequestContext requestContext) throws ResponseException {
        throw new ResourceDoesNotSupportOperationException("Purge not supported on SurgicalAppointment resource");
    }

    @Override
    public DelegatingResourceDescription getRepresentationDescription(Representation representation) {
        if ((representation instanceof DefaultRepresentation) || (representation instanceof RefRepresentation)) {
            DelegatingResourceDescription description = new DelegatingResourceDescription();
            description.addProperty("id");
            description.addProperty("patient", Representation.DEFAULT);
            description.addProperty("actualStartDatetime");
            description.addProperty("actualEndDatetime");
            description.addProperty("status");
            description.addProperty("notes");
            return description;
        }
        if ((representation instanceof FullRepresentation)) {
            DelegatingResourceDescription description = new DelegatingResourceDescription();
            description.addProperty("id");
            description.addProperty("patient", Representation.FULL);
            description.addProperty("actualStartDatetime");
            description.addProperty("actualEndDatetime");
            description.addProperty("status");
            description.addProperty("notes");
            return description;
        }
        return null;
    }

    @Override
    public DelegatingResourceDescription getCreatableProperties() {
        DelegatingResourceDescription delegatingResourceDescription = new DelegatingResourceDescription();
        delegatingResourceDescription.addProperty("id");
        delegatingResourceDescription.addRequiredProperty("patient");
        delegatingResourceDescription.addRequiredProperty("surgicalBlock");
        delegatingResourceDescription.addProperty("actualStartDatetime");
        delegatingResourceDescription.addProperty("actualEndDatetime");
        delegatingResourceDescription.addProperty("status");
        delegatingResourceDescription.addProperty("notes");
        return delegatingResourceDescription;
    }
}
