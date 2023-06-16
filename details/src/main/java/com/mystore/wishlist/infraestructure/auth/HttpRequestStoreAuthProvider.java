package com.mystore.wishlist.infraestructure.auth;

import com.mystore.shared.core.auth.PrincipalUser;
import com.mystore.shared.core.auth.StoreAuthProvider;
import jakarta.servlet.http.HttpServletRequest;

/**
 * Basic implementation of {@link StoreAuthProvider} that uses the {@link HttpServletRequest} to get the current user.
 */
public class HttpRequestStoreAuthProvider implements StoreAuthProvider {

    private final String clientId;
    private final boolean clientIsPremium;

    public HttpRequestStoreAuthProvider(HttpServletRequest request) {
        var clientIdFromHeader = request.getHeader("X-Client-Id");
        clientId = clientIdFromHeader == null ? "samuel" : clientIdFromHeader;
        clientIsPremium = "true".equals(request.getHeader("X-Client-Premium"));
    }

    @Override
    public PrincipalUser getCurrentUser() {
        return new PrincipalUser(clientId, "Mocked User Name", "mocked.user@email.com", clientIsPremium);
    }

}
