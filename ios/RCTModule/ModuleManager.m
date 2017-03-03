#import "ModuleManager.h"


@implementation ModuleManager

static ModuleManager* _instance = nil;

+(instancetype) shareInstance
{
    static dispatch_once_t onceToken;
    dispatch_once(&onceToken, ^{
        _instance = [[self alloc] init];
    });
    
    return _instance ;
}
@end
