package org.gluu.idp.consent.processor;

import java.io.Serializable;
import java.util.Map;

import net.shibboleth.idp.attribute.IdPAttribute;

/**
 * Post process attributes context
 *
 * @author Yuriy Movchan
 * @version 0.1, 06/22/2020
 */
public class PostProcessAttributesContext implements Serializable {

	private static final long serialVersionUID = 1822377169827670256L;

	private GluuReleaseAttributesPostProcessor releaseAttributesPostProcessor;
	private Map<String,IdPAttribute> idpAttributeMap;

	
	public void setAttributeReleaseAction(GluuReleaseAttributesPostProcessor releaseAttributesPostProcessor) {
		this.releaseAttributesPostProcessor = releaseAttributesPostProcessor;
	}

	public GluuReleaseAttributesPostProcessor getGluuReleaseAttributesPostProcessor() {
		return releaseAttributesPostProcessor;
	}

	public void setGluuReleaseAttributesPostProcessor(GluuReleaseAttributesPostProcessor releaseAttributesPostProcessor) {
		this.releaseAttributesPostProcessor = releaseAttributesPostProcessor;
	}

	public void setIdpAttributeMap(Map<String,IdPAttribute> idpAttributeMap) {

		this.idpAttributeMap = idpAttributeMap;
	}

	public Map<String,IdPAttribute> getIdpAttributeMap() {

		return this.idpAttributeMap;
	}
}
