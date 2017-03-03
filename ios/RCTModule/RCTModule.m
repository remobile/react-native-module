#import <React/RCTRootView.h>
#import "ModuleManager.h"
#import "RCTModule.h"

@implementation RCTModule

RCT_EXPORT_MODULE();

RCT_EXPORT_METHOD(load:(NSString *)url moduleName:(NSString *)moduleName props:(NSDictionary *)props callback:(RCTResponseSenderBlock)callback)
{
    dispatch_async(dispatch_get_main_queue(), ^{
        self.callback = callback;
        self.navigationController = [[UIViewController alloc] init];
        [ModuleManager shareInstance].module = self;
        RCTRootView *rootView = [[RCTRootView alloc] initWithBundleURL: [NSURL URLWithString:url]
                                                            moduleName: moduleName
                                                     initialProperties: props
                                                         launchOptions: nil];
        self.navigationController.view = rootView;
        
        [[UIApplication sharedApplication].delegate.window.rootViewController presentViewController: self.navigationController animated: NO completion: nil];
    });
}

RCT_EXPORT_METHOD(unload:(NSDictionary *)props)
{
    dispatch_async(dispatch_get_main_queue(), ^{
        RCTModule *module = [ModuleManager shareInstance].module;
        [module.navigationController dismissViewControllerAnimated: NO completion: ^{
            module.callback(@[props]);
        }];
    });
}

@end
