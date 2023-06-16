package com.mystore.wishlist.apprest.config;

import com.mystore.shared.core.IMessageSource;
import com.mystore.shared.core.auth.StoreAuthProvider;
import com.mystore.shared.core.event.IEventDispatcher;
import com.mystore.shared.core.util.MessageUtil;
import com.mystore.wishlist.core.repository.WishlistDataSource;
import com.mystore.wishlist.core.service.*;
import com.mystore.wishlist.core.service.impl.*;
import com.mystore.wishlist.infraestructure.auth.HttpRequestStoreAuthProvider;
import com.mystore.wishlist.infraestructure.event.EventDispatcherNoOpImpl;
import com.mystore.wishlist.infraestructure.repository.mongo.WishlistDataSourceMongoImpl;
import com.mystore.wishlist.infraestructure.repository.mongo.WishlistMapper;
import com.mystore.wishlist.infraestructure.repository.mongo.WishlistMongoRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.annotation.RequestScope;

import java.util.Locale;

@Configuration
public class BeanConfig {

    // ---------- Auth ----------
    @Bean
    @RequestScope
    public StoreAuthProvider storeAuthProvider(HttpServletRequest request) {
        return new HttpRequestStoreAuthProvider(request);
    }

    // ---------- Events Dispatcher ----------
    @Bean
    public IEventDispatcher eventDispatcher() {
        return new EventDispatcherNoOpImpl();
    }

//    @Bean
//    public IEventDispatcher eventDispatcher(RabbitTemplate rabbitTemplate) {
//        return new EventDispatcherRabbitMQImpl(rabbitTemplate);
//    }

    // ---------- DataSource's ----------
    @Bean
    public WishlistDataSource wishlistDataSource(WishlistMongoRepository repository,
                                                 WishlistMapper mapper) {
        return new WishlistDataSourceMongoImpl(repository, mapper);
    }

    // ---------- Service's ----------
    @Bean
    public AddProductToWishlistService addProductToWishlistService(StoreAuthProvider storeAuthProvider,
                                                                   CreateNewWishlistService createNewWishlistService,
                                                                   WishlistDataSource wishlistDataSource,
                                                                   IEventDispatcher eventDispatcher) {
        return new AddProductToWishlistServiceImpl(storeAuthProvider, createNewWishlistService, wishlistDataSource, eventDispatcher);
    }

    @Bean
    public CreateNewWishlistService createNewWishlistService(IEventDispatcher eventDispatcher,
                                                             StoreAuthProvider storeAuthProvider,
                                                             WishlistDataSource wishlistDataSource) {
        return new CreateNewWishlistServiceImpl(eventDispatcher, storeAuthProvider, wishlistDataSource);
    }

    @Bean
    public GetProductsFromWishlistService getProductsFromWishlistService(StoreAuthProvider storeAuthProvider,
                                                                         WishlistDataSource wishlistDataSource) {
        return new GetProductsFromWishlistServiceImpl(storeAuthProvider, wishlistDataSource);
    }

    @Bean
    public HasProductInWishlistService hasProductInWishlistService(StoreAuthProvider storeAuthProvider,
                                                                   WishlistDataSource wishlistDataSource) {
        return new HasProductInWishlistServiceImpl(storeAuthProvider, wishlistDataSource);
    }

    @Bean
    public RemoveProductFromWishlistService removeProductFromWishlistService(IEventDispatcher eventDispatcher,
                                                                             StoreAuthProvider storeAuthProvider,
                                                                             WishlistDataSource wishlistDataSource) {
        return new RemoveProductFromWishlistServiceImpl(eventDispatcher, storeAuthProvider, wishlistDataSource);
    }

    // ---------- Message Source ----------
    @Bean(name = "myStoreMessageSourceImpl", autowireCandidate = false)
    public IMessageSource messageSourceImpl(MessageSource messageSource) {
        IMessageSource impl = (defaultMessage, code, args) -> {
            if (StringUtils.isNotBlank(code)) {
                try {
                    return messageSource.getMessage(code, args, Locale.getDefault());
                } catch (NoSuchMessageException e) {
                    // ignore
                }
            }

            return defaultMessage;
        };

        MessageUtil.setMessageSource(impl);
        return impl;
    }
}
