/*
 * Copyright 2008-2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.broadleafcommerce.vendor.usps.domain;

import org.broadleafcommerce.vendor.usps.domain.type.USPSServiceType;
import org.broadleafcommerce.common.presentation.AdminPresentation;
import org.broadleafcommerce.common.presentation.AdminPresentationClass;
import org.broadleafcommerce.common.presentation.client.SupportedFieldType;
import org.broadleafcommerce.core.order.domain.FulfillmentOptionImpl;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

/**
 * JPA Domain representing USPS-specific fulfillment option
 * <p/>
 * User: Kelly Tisdell
 * Date: 11/07/12
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "BLC_USPS_FULFILLMENT_OPTION")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "blStandardElements")
@AdminPresentationClass(friendlyName = "USPS Fulfillment Option")
public class USPSFulfillmentOptionImpl extends FulfillmentOptionImpl implements USPSFulfillmentOption {

    private static final long serialVersionUID = 1L;

    @Column(name="USPS_SERVICE_TYPE", nullable = false)
    @AdminPresentation(friendlyName = "USPS Service Type", fieldType= SupportedFieldType.BROADLEAF_ENUMERATION, broadleafEnumeration="org.broadleafcommerce.vendor.usps.domain.type.USPSServiceType")
    protected String service;

    public USPSServiceType getService() {
        return USPSServiceType.getInstance(service);
    }

    public void setService(USPSServiceType service) {
        this.service = service.getType();
    }
}
