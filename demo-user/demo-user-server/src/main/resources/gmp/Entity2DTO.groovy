import com.hwj.demo.user.authority.api.dto.UserApiDTO
import com.hwj.demo.user.base.entity.User

mappers {


    mapper {
        from User to UserApiDTO converter { User user, UserApiDTO dto ->
            dto.recId = user.recId
            dto.code = user.code
            dto.name = user.name
            dto.mobile = user.mobile
            dto.email = user.email
            dto.enabled = user.enabled
            dto.recCreatedBy = user.recCreatedBy
            dto.recCreatedOrg = user.recCreatedOrg
            dto.recCreatedTime = user.recCreatedTime
            dto.recModifiedBy = user.recModifiedBy
            dto.recModifiedOrg = user.recModifiedOrg
            dto.recModifiedTime = user.recModifiedTime
            dto.recVersion = user.recVersion
        }
    }

}

