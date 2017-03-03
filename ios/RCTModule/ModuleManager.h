#import <UIKit/UIKit.h>

@class RCTModule;

@interface ModuleManager : NSObject
@property (nonatomic, strong) RCTModule *module;

+(instancetype) shareInstance ;
@end
