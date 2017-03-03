#import <React/RCTBridgeModule.h>

@interface RCTModule: NSObject <RCTBridgeModule>
@property (nonatomic, strong) UINavigationController *navigationController;
@property (nonatomic, strong) RCTResponseSenderBlock callback;
@end
