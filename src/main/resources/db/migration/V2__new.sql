GO
CREATE PROCEDURE uspFindById
(
	@id BIGINT
)
AS
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;

SELECT FirstName, LastName, [Password], Email, PhoneNumber, CompanySize
FROM Users
WHERE (UserId = @id)
END