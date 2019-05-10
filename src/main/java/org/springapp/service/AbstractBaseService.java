package org.springapp.service;

import com.google.gson.Gson;

public abstract class AbstractBaseService {
    //protected final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    
    protected final Gson gson = new Gson();
}
